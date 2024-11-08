package com.example.cv.config;

public enum ReportTypeEnum {
    PDF("pdf", ".pdf"),
    DOCX("docx", ".docx"),
    CSV("csv", ".csv"),
    XLSX("xlsx", ".xlsx"),
    HTML("html", ".html"),
    XML("xml", ".xml");
    private final String code;
    private final String extension;

    // Constructor
    ReportTypeEnum(String code, String extension) {
        this.code = code;
        this.extension = extension;
    }

    // Getter for the extension
    public String getExtension() {
        return extension;
    }

    // Method to get ReportTypeEnum by code
    public static ReportTypeEnum getReportTypeByCode(String code) {
        for (ReportTypeEnum reportType : values()) {
            if (reportType.code.equalsIgnoreCase(code)) {
                return reportType;
            }
        }
        throw new IllegalArgumentException("Invalid file type: " + code);
    }
}
