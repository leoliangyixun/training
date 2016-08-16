package org.mybatis.generator.api;

import java.io.File;

/**
 * mybatis配置的文件信息
 * 
 * @author 丹青生
 *
 * @date 2015-9-2
 */
public abstract class GeneratedFile {

	protected String targetProject;

    public GeneratedFile(String targetProject) {
        super();
        this.targetProject = new File("").getAbsolutePath();
        if("${targetProject}".equalsIgnoreCase("${targetProject}")){ // 针对targetProject="${targetProject}"这样的maven格式配置
            String folder = String.format("%csrc%cmain%cjava", File.separatorChar, File.separatorChar, File.separatorChar);
            targetProject = new File(this.targetProject + folder).exists() ? this.targetProject + folder : this.targetProject;
        }
        this.targetProject = targetProject;
    }

    public abstract String getFormattedContent();

    public abstract String getFileName();

    public String getTargetProject() {
        return targetProject;
    }

    public abstract String getTargetPackage();

    @Override
    public String toString() {
        return getFormattedContent();
    }

    public abstract boolean isMergeable();
	
}

