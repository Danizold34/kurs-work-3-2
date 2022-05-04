package com.nyha.webfinal.controller.command;

import com.nyha.webfinal.controller.command.impl.*;

import java.util.Optional;

/**
 * Describes all command types
 *
 * @author Dovlet Jumaev
 */
public enum CommandType {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    CHANGE_PASSWORD {
        {
            this.command = new ChangePasswordCommand();
        }
    },
    CHANGE_ROLE {
        {
            this.command = new ChangeRoleCommand();
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    CHANGE_LOCALE {
        {
            this.command = new ChangeLocaleCommand();
        }
    },
    SHOW_ALL_TRAINS {
        {
            this.command = new ShowAllTrainsCommand();
        }
    },
    FIND_TRAINS_BY_STATIONS {
        {
            this.command = new FindTrainsByStationsCommand();
        }
    },
    FIND_TRAIN_BY_ID {
        {
            this.command = new FindTrainByIdCommand();
        }
    },
    BUY_TICKET {
        {
            this.command = new BuyTicketCommand();
        }
    },
    ADD_TRAIN {
        {
            this.command = new AddTrainCommand();
        }
    },
    TO_BUY_TICKET_PAGE {
        {
            this.command = new ToBuyTicketPageCommand();
        }
    },
    TO_MAIN_PAGE {
        {
            this.command = new ToMainPageCommand();
        }
    },
    TO_REGISTRATION_PAGE {
        {
            this.command = new ToRegistrationPageCommand();
        }
    },
    TO_MY_TICKETS_PAGE {
        {
            this.command = new ToMyTicketsPageCommand();
        }
    },
    TO_PROFILE_PAGE {
        {
            this.command = new ToProfilePageCommand();
        }
    },
    TO_ADMIN_PAGE {
        {
            this.command = new ToAdminPageCommand();
        }
    },
    TO_USERS_PAGE {
        {
            this.command = new ToUsersPageCommand();
        }
    },
    TO_ADD_TRAIN_PAGE {
        {
            this.command = new ToAddTrainPageCommand();
        }
    },
    TO_LOGIN_PAGE {
        {
            this.command = new ToLoginPageCommand();
        }
    };

    Command command;

    public Command getCurrentCommand() {
        return command;
    }
}
