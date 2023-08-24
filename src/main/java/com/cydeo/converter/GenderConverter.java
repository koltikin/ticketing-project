package com.cydeo.converter;
import com.cydeo.enums.Gender;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ConfigurationPropertiesBinding
public class GenderConverter implements Converter<String, Gender> {
    public Gender convert(String text) {
        for (Gender gender : Gender.values()) {
            if (gender.getValue().equalsIgnoreCase(text)) {
                return gender;
            }
        }
        return null;
    }
}