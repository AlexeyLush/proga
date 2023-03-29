package ru.itmo.commands;


import ru.itmo.commands.interactives.add.AddInteractiveCoordinate;
import ru.itmo.commands.interactives.add.AddInteractiveLabWork;
import ru.itmo.commands.interactives.add.AddInteractivePerson;
import ru.itmo.commands.interactives.utills.QuestionInteractive;
import ru.itmo.commands.request.CommandRequest;
import ru.itmo.commands.request.RequestType;
import ru.itmo.managers.commandManager.CommandManager;
import ru.itmo.models.Coordinates;
import ru.itmo.models.LabWork;
import ru.itmo.models.Person;

import java.util.Scanner;

public class RemoveGreaterCommand extends Command{
    private final Scanner scanner;
    public RemoveGreaterCommand(Scanner scanner) {
        this.scanner = scanner;
        setTargetTitleForUserInput("remove_greater");
        setDescription("remove_greater {element} : удалить из коллекции все элементы, превышающие заданный");
    }
    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {

        LabWork labWork = new LabWork();

        AddInteractiveLabWork.interactiveName(labWork, scanner);

        Coordinates coordinates = new Coordinates();

        AddInteractiveCoordinate.interactiveX(coordinates, scanner);
        AddInteractiveCoordinate.interactiveY(coordinates, scanner);

        labWork.setCoordinates(coordinates);

        AddInteractiveLabWork.interactiveMinimalPoint(labWork, scanner);
        AddInteractiveLabWork.interactiveMaximumPoint(labWork, scanner);


        if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить сложность работы?", scanner)) {
            AddInteractiveLabWork.interactiveDifficulty(labWork, scanner);
        }

        if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить автора?", scanner)) {
            Person author = new Person();
            AddInteractivePerson.interactivePerson(author, scanner);
            labWork.setAuthor(author);
        }

        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setObject(labWork);
        commandRequest.setRequestType(RequestType.DELETE_GREATER);
        return commandRequest;
    }
}
