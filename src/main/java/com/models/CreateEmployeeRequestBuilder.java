package com.models;

public class CreateEmployeeRequestBuilder {

    private CreateEmployeeRequest employeeRequest;

    private CreateEmployeeRequestBuilder() {
        employeeRequest = new CreateEmployeeRequest();
    }

    public static CreateEmployeeRequestBuilder aEmployee() {
        return new CreateEmployeeRequestBuilder();

    }

    public CreateEmployeeRequestBuilder withName(String name) {
        this.employeeRequest.setName(name);
        return this;
    }

    public CreateEmployeeRequestBuilder withSalary(String salary) {
        this.employeeRequest.setSalary(salary);
        return this;
    }

    public CreateEmployeeRequestBuilder withAge(String age) {
        this.employeeRequest.setAge(age);
        return this;
    }

    public CreateEmployeeRequest build() {
        return employeeRequest;
    }
}
