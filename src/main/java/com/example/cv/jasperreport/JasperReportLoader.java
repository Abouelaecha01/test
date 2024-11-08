package com.example.cv.jasperreport;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Component;

import java.io.InputStream;

//@Component
//public class JasperReportLoader {
//    public JasperReport loadReport(String path) throws JRException {
//        InputStream reportStream = this.getClass().getResourceAsStream(path);
//        if (reportStream == null) {
//            throw new RuntimeException("Jasper report not found at path: " + path);
//        }
//        return (JasperReport) JRLoader.loadObject(reportStream);
//    }

@Component
public class JasperReportLoader {

    public JasperReport loadReport(String path) throws JRException {
        InputStream reportStream = this.getClass().getResourceAsStream(path);
        if (reportStream == null) {
            throw new RuntimeException("Jasper report not found at path: " + path);
        }
        return JasperCompileManager.compileReport(reportStream);// "compileReport method kayakhod template dyal JasperReports (fi format JRXML) w kaycompileha l JasperReport object (java object)
    }
}

