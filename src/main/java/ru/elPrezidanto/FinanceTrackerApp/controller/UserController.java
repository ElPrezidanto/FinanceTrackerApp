package ru.elPrezidanto.FinanceTrackerApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import ru.elPrezidanto.FinanceTrackerApp.controller.common.ICrudController;
import ru.elPrezidanto.FinanceTrackerApp.model.dto.UserDTO;

/**
 * REST-интерфейс пользователя
 */
@RequestMapping("/user")
public interface UserController extends ICrudController<UserDTO, String> {

}
