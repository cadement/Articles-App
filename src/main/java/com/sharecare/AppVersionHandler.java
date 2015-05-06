package com.sharecare;

import info.magnolia.module.DefaultModuleVersionHandler;
import info.magnolia.module.InstallContext;
import info.magnolia.module.delta.ModuleBootstrapTask;
import info.magnolia.module.delta.ModuleFilesExtraction;
import info.magnolia.module.delta.Task;

import java.util.ArrayList;
import java.util.List;

public class AppVersionHandler extends DefaultModuleVersionHandler {

    @Override
    protected List<Task> getStartupTasks(InstallContext installContext) {
        final List<Task> tasks = new ArrayList<Task>();
        if ("SNAPSHOT".equalsIgnoreCase(installContext.getCurrentModuleDefinition().getVersion().getClassifier())) {

            log.warn("Starting SNAPSHOT update");
            tasks.add(new ModuleBootstrapTask());
            tasks.add(new ModuleFilesExtraction());

        }
        return tasks;
    }

}
