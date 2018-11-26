package utils.interfaces;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.util.Date;

public interface WriteLogs {
    @Step
    default void writeLogs() {
        LogEntries logEntries = getLogs();
        StringBuilder result = new StringBuilder();
        for (LogEntry entry : logEntries) {
            result.append(new Date(entry.getTimestamp())).append(" ").append(entry.getMessage()).append("\n");
        }
        if (!result.toString().equals("")) {
            logs(result.toString());
            Assert.fail("Js errors detected");
        }
    }

    LogEntries getLogs();

    @Attachment
    default String logs(String log) {
        return log;
    }
}
