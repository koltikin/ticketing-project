package com.cydeo.converter;
import com.cydeo.dto.RoleDTO;
import com.cydeo.enums.Gender;
import com.cydeo.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ConfigurationPropertiesBinding
public class RoleDtoConverter implements Converter<String, RoleDTO> {
    private final RoleService roleService;
    @Override
    public RoleDTO convert(String id) {
        if(id.equals("")) return null;
        return roleService.findById(Long.parseLong(id));
    }

}


