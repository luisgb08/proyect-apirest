package com.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import static com.tasks.Post2.openDataForm;


public class openUploadDataForm implements Task {

    private final String urlPage;

    public openUploadDataForm(String urlPage) {
        this.urlPage = urlPage;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        openDataForm(urlPage);
    }


    public static openUploadDataForm openUploadDataFormWithThe(String urlPage) {
        return Tasks.instrumented(openUploadDataForm.class, urlPage);
    }
}
