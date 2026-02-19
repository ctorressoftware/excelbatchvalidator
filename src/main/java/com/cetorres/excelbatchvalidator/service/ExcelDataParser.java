package com.cetorres.excelbatchvalidator.service;

import com.cetorres.excelbatchvalidator.domain.Person;
import org.springframework.stereotype.Component;
import static com.cetorres.excelbatchvalidator.enums.DataColumn.*;
import java.util.List;

@Component
public class ExcelDataParser {

    public List<Person> transform(List<List<String>> stringDataList) {
        return stringDataList.stream().map(list ->
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
}
