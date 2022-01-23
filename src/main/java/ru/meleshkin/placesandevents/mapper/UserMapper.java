package ru.meleshkin.placesandevents.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.meleshkin.placesandevents.domain.dto.UserCreateDto;
import ru.meleshkin.placesandevents.domain.dto.UserDto;
import ru.meleshkin.placesandevents.domain.entity.User;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper
public interface UserMapper {

    User fromDto(UserCreateDto source);

    User fromDto(UserDto source);
    UserDto toDto(User source);

    List<User> fromDtoList(List<UserDto> source);
    List<UserDto> toDtoList(List<User> source);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    User merge(@MappingTarget User target, User source);
}
