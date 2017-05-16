package io.github.xinyangpan.codegen.core.template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;

import com.google.common.io.Closeables;
import com.google.common.io.Files;

import freemarker.template.Template;

public interface HasTemplateType {

	TemplateType templateType();

	default String getRelativeFileName() {
		return "";
	}

	default void processToConsole() {
		this.processTo(new OutputStreamWriter(System.out));
	}

	default void processTo(Writer writer) {
		try {
			TemplateHelper templateHelper = TemplateHelper.getInstance();
			Template template = templateHelper.getTemplate(templateType());
			template.process(this, writer);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	default String processToString() {
		StringWriter sw = new StringWriter();
		this.processTo(sw);
		return sw.toString();
	}

	default void processToFile(String sourceDir) {
		String relativeFileName = this.getRelativeFileName();
		String pathname = String.format("%s/%s", sourceDir, relativeFileName);
		FileWriter fileWriter = null;
		try {
			File file = new File(pathname);
			Files.createParentDirs(file);
			fileWriter = new FileWriter(file);
			this.processTo(fileWriter);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				Closeables.close(fileWriter, true);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
