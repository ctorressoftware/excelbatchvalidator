package com.cetorres.excelbatchvalidator.service;

import com.cetorres.excelbatchvalidator.domain.Person;
import com.cetorres.excelbatchvalidator.domain.ValidationItem;
import com.cetorres.excelbatchvalidator.enums.DataType;
import org.springframework.stereotype.Component;

import static com.cetorres.excelbatchvalidator.enums.DataColumn.*;

import java.util.List;

@Component
public class ExcelDataParser {

    public List<Person> transform(List<List<String>> stringDataList) {
        return stringDataList.stream()
                .map(list ->
                        Person.builder()
                                .dni(list.get(DNI.getIndex()))
                                .firstName(list.get(FIRST_NAME.getIndex()))
                                .secondName(list.get(SECOND_NAME.getIndex()))
                                .firstLastname(list.get(FIRST_LASTNAME.getIndex()))
                                .secondLastname(list.get(SECOND_LASTNAME.getIndex()))
                                .gender(list.get(GENDER.getIndex()).charAt(0))
                                .email(list.get(EMAIL.getIndex()))
                                .phoneNumber(list.get(PHONE_NUMBER.getIndex()))
                                .build())
                .toList();
    }

    // TODO: Change the datatype mapping and create a specific validator for each case
    public List<List<ValidationItem>> buildValidationItems(List<Person> persons) {
        return persons.stream()
                .map(person -> List.of(
                        new ValidationItem(DataType.DNI, person.getDni()),
                        new ValidationItem(DataType.DNI, person.getFirstName()),
                        new ValidationItem(DataType.DNI, person.getSecondName()),
                        new ValidationItem(DataType.DNI, person.getFirstLastname()),
                        new ValidationItem(DataType.DNI, person.getSecondLastname()),
                        new ValidationItem(DataType.DNI, String.valueOf(person.getGender())),
                        new ValidationItem(DataType.DNI, person.getEmail()),
                        new ValidationItem(DataType.DNI, person.getPhoneNumber())))
                .toList();
    }
}
