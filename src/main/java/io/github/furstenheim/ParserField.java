package io.github.furstenheim;

class ParserField {
    String name;
    Integer index;
    // ?Value
    Delimiter delimiter;
    Delimiter elemDelimiter;
    String indexedByString;
    Integer indexedBy;

    public ParserField(String name, Integer index, Delimiter delimiter, Delimiter elemDelimiter,
            String indexedByString) {
        this.name = name;
        this.index = index;
        this.delimiter = delimiter;
        this.elemDelimiter = elemDelimiter;
        this.indexedByString = indexedByString;
    }
}
