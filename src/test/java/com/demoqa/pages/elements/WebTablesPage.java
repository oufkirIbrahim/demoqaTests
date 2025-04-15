package com.demoqa.pages.elements;

import com.demoqa.model.User;
import org.openqa.selenium.By;

public class WebTablesPage extends ElementsPage{

    String addButtonName = "addButton";
    String editButtonName = "editButton";
    String ageFieldName = "ageField";
    String submitButtonName = "submitButton";
    String ageColumnName = "ageColumn";

    public void clickAddButton() {
        By addButton = locatorReader.getLocator(addButtonName);
        click(addButton);
    }

    public void addNewUser(User user) {
        By firstNameField = locatorReader.getLocator("firstNameField");
        By lastNameField = locatorReader.getLocator("lastNameField");
        By emailField = locatorReader.getLocator("emailField");
        By ageField = locatorReader.getLocator("addAgeField");
        By salaryField = locatorReader.getLocator("salaryField");
        By departmentField = locatorReader.getLocator("departmentField");
        By submitButton = locatorReader.getLocator(submitButtonName);

        setText(firstNameField, user.getFirstName());
        setText(lastNameField, user.getLastName());
        setText(emailField, user.getEmail());
        setText(ageField, user.getAge());
        setText(salaryField, user.getSalary());
        setText(departmentField, user.getDepartment());
        click(submitButton);
    }

    public void clickEditButton(String email) {
        By editButton = locatorReader.getLocator(editButtonName, email);
        click(editButton);
    }

    public void modifyAge(String age) {
        By ageField = locatorReader.getLocator(ageFieldName);
        By submitButton = locatorReader.getLocator(submitButtonName);
        setText(ageField, age);
        click(submitButton);
    }

    public String getAge(String email) {
        By ageField = locatorReader.getLocator(ageColumnName, email);
        return getText(ageField);
    }

    public boolean checkIsUserDisplayed(String email) {
        By userRow = locatorReader.getLocator("userRow", email);
        return isElementPresent(userRow);
    }

    public void deleteUser(String email) {
        By deleteButton = locatorReader.getLocator("deleteButton", email);
        click(deleteButton);
    }

    public void searchUser(String email) {
        By searchField = locatorReader.getLocator("searchField");
        setText(searchField, email);
    }


}
