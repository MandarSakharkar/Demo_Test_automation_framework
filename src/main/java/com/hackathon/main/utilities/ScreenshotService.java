package com.hackathon.main.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;


@Service
public class ScreenshotService {

    @Autowired
    private ApplicationContext context;

    @Value("${screenshot.path}")
    private Path path;

    public void takeScreenshot(String fileName) throws IOException {
        File sourceFile = this.context.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.FILE);
        FileCopyUtils.copy(sourceFile, this.path.resolve(fileName+".png").toFile());
    }

    public byte[] getScreenshot(){
        return this.context.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.BYTES);
    }

}
