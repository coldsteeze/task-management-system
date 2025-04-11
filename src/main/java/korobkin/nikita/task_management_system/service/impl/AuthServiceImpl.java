package korobkin.nikita.task_management_system.service.impl;

import korobkin.nikita.task_management_system.service.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    @Override
    public String register() {
        return "User registered";
    }
}
