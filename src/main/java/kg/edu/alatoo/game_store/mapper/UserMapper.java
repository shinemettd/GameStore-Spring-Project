package kg.edu.alatoo.game_store.mapper;

import kg.edu.alatoo.game_store.entity.User;
import kg.edu.alatoo.game_store.payload.auth.AuthSignUpRequest;
import kg.edu.alatoo.game_store.payload.auth.AuthSignUpResponse;
import kg.edu.alatoo.game_store.payload.user.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserGetResponse toModel(User user);

    AuthSignUpResponse signUpToModel(User user);
    User signUpToEntity(AuthSignUpRequest authSignUpRequest);

    UserUpdateResponse updateToModel(User user);

    UserBalanceResponse balanceToModel(User user);
}
