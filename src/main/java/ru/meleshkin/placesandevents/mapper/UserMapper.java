package ru.meleshkin.placesandevents.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.meleshkin.placesandevents.domain.dto.UserDto;
import ru.meleshkin.placesandevents.domain.dto.UserInfoDto;
import ru.meleshkin.placesandevents.domain.entity.User;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper
public interface UserMapper {
    User fromDto(UserDto source);
    UserDto toDto(User source);

    List<User> fromDtoList(List<UserDto> userDtoList);
    List<UserDto> toDtoList(List<User> userList);

    User fromInfoDto(UserInfoDto source);
    UserInfoDto toInfoDto(User source);

    List<User> fromInfoDtoList(List<UserInfoDto> userDtoList);
    List<UserInfoDto> toInfoDtoList(List<User> userList);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    User merge(@MappingTarget User target, User source);
}
