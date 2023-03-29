package ru.itmo.commands;


import ru.itmo.commands.interactives.show.ShowInteractiveLabWork;
import ru.itmo.commands.request.CommandRequest;
import ru.itmo.commands.request.RequestType;
import ru.itmo.commands.response.CommandResponse;
import ru.itmo.managers.commandManager.CommandManager;
import ru.itmo.models.LabWork;

import java.util.ArrayDeque;

public class ShowCommand extends Command{

    public ShowCommand() {
        setTargetTitleForUserInput("show");
        setDescription("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {
        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setRequestType(RequestType.GET);

        CommandResponse commandResponse = commandManager.getResponse(commandRequest);

        Class typeOfObjectInResponse = commandResponse.getTypeOfObject();
        if (typeOfObjectInResponse == ArrayDeque.class) {
            ArrayDeque<LabWork> labWorks = (ArrayDeque<LabWork>) commandResponse.getObject();


            if (labWorks.size() > 0) {
                System.out.println("---------------------------------------------------------------------------");
                for (LabWork labWork : labWorks) {
                    ShowInteractiveLabWork.showLabWork(labWork, 0);
                    System.out.println("---------------------------------------------------------------------------");
                }
            }


            else {
                System.out.println("В списке нет лабораторных работ");
            }

        }

        commandRequest.setRequestType(RequestType.INTERNAL);
        return commandRequest;
    }
}
