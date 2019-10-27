package cn.nealian.RexForNumRange.generator;

import java.util.*;

public class FakeRexBuilder implements IRexBuilder {

    private StringBuilder pattern;
    private List<Long> elements;
    private int places;

    private static List<String> PATTERNS = Arrays.asList(
            "[]",
            "N.[]",
            "N.[]|N.[]",
            "N.[]|[].[]|N.[]");

    @Override
    public IRexBuilder createNew() {
        this.pattern = new StringBuilder();
        this.elements = new ArrayList<>();
        return this;
    }

    @Override
    public IRexBuilder number(long number) {
        this.pattern.append("N");
        this.elements.add(number);
        return this;
    }

    @Override
    public IRexBuilder rangeFull(int decimalPlaces) {
        places = decimalPlaces;
        return range(0, Integer.parseInt(String.join("", Collections.nCopies(decimalPlaces, "9"))));
    }

    @Override
    public IRexBuilder rangeToCeiling(long start, int decimalPlaces) {
        places = decimalPlaces;
        return range(start, Integer.parseInt(String.join("", Collections.nCopies(decimalPlaces, "9"))));
    }

    @Override
    public IRexBuilder range(long min, long max) {
        this.pattern.append("[]");
        this.elements.add(min);
        this.elements.add(max);
        return this;
    }

    @Override
    public IRexBuilder or() {
        this.pattern.append("|");
        return this;
    }


    @Override
    public IRexBuilder dot() {
        this.pattern.append(".");
        return this;
    }

    public Range deRange(long start, long end) {
        return new Range(start, end);
    }

    public Range deRange(long number, long start, long end) {
        return new Range(
                Double.parseDouble(number + "." + String.valueOf(start)),
                Double.parseDouble(number + "." + String.valueOf(end))
        );
    }

    public Range deRange(long startInteger, long endInteger, int startDecimal, int endDecimal) {
        return new Range(
                Double.parseDouble(startInteger + "." + String.valueOf(startDecimal)),
                Double.parseDouble(endInteger + "." + String.valueOf(endDecimal))
        );
    }

    class Range {
        double start;
        double end;

        public Range(double start, double end) {
            this.start = start;
            this.end = end;
        }
    }

    private Range merge(List<Range> ranges, int places) {
        return ranges.stream().sorted(Comparator.comparing(r -> r.start)).reduce((rangeA, rangeB) -> {
            if (rangeB.start - rangeA.end - Math.pow(0.1, places) <= Math.pow(0.1, places + 1)) {
                return new Range(rangeA.start, Math.max(rangeA.end, rangeB.end));
            }
            throw new RuntimeException();
        }).get();
    }

    @Override
    public String build() {
        List<Range> ranges = new ArrayList<>();
        Iterator<Long> ele = elements.iterator();
        for (String s : this.pattern.toString().split("\\|")) {
            switch (s) {
                case "[]":
                    ranges.add(deRange(ele.next(), ele.next()));
                    break;
                case "N.[]":
                    ranges.add(deRange(ele.next(), ele.next(), ele.next()));
                    break;
                case "[].[]":
                    ranges.add(deRange(ele.next(), ele.next(), ele.next().intValue(), ele.next().intValue()));
                    break;
                default:
                    throw new RuntimeException();
            }
        }
        Range r = merge(ranges, places);
        String format = "[%." + places + "f, %." + places + "f]";
        return String.format(format, r.start, r.end);
    }

}
