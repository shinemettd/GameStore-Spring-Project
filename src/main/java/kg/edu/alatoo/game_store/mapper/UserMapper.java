package kg.edu.alatoo.game_store.mapper;

import kg.edu.alatoo.game_store.entity.User;
import kg.edu.alatoo.game_store.payload.user.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserGetResponse toModel(User user);

    UserSignUpResponse signUpToModel(User user);
    User signUpToEntity(UserSignUpRequest userSignUpRequest);

    UserUpdateResponse updateToModel(User user);

    UserBalanceResponse balanceToModel(User user);
}
