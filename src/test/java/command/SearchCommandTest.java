package command;

import common.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Assignment;
import tasks.Event;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author joelczk
public class SearchCommandTest {
    public static final DateTimeFormatter INPUT_DATE_ONLY_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yy");
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static TaskList filledTaskList;
    private static TaskList emptyTaskList;
    private static Ui ui;
    private StringBuilder searchString;
    private static String stringDate;
    private static String stringDateTime1;
    private static String stringDateTime2;
    private static String stringDateTime3;
    private static String stringDateTime4;
    private static LocalDate testDate1;
    private static LocalDateTime testDateTime1;
    private static LocalDateTime testDateTime2;
    private static LocalDateTime testDateTime3;
    private static LocalDateTime testDateTime4;
    private static Assignment testCaseOne;
    private static Assignment testCaseTwo;
    private static Assignment testCaseThree;
    private static Assignment testCaseSeven;
    private static Event testCaseFour;
    private static Event testCaseFive;
    private static Event testCaseSix;

    /**
     * Set up variables for tests.
     */
    @BeforeEach
    public void setup() {
        emptyTaskList = new TaskList();
        filledTaskList = new TaskList();
        ui = new Ui();
        searchString = new StringBuilder();
        stringDate = "13/03/20";
        stringDateTime1 = "13/03/2020 18:00";
        stringDateTime2 = "13/03/2020 20:30";
        stringDateTime3 = "01/01/2020 00:00";
        stringDateTime4 = "01/01/2020 02:59";
        testDate1 = LocalDate.parse(stringDate,INPUT_DATE_ONLY_FORMAT);
        testDateTime1 = LocalDateTime.parse(stringDateTime1, dateTimeFormatter);
        testDateTime2 = LocalDateTime.parse(stringDateTime2, dateTimeFormatter);
        testDateTime3 = LocalDateTime.parse(stringDateTime3, dateTimeFormatter);
        testDateTime4 = LocalDateTime.parse(stringDateTime4, dateTimeFormatter);

        testCaseOne = new Assignment("Test 3", "CS2102", testDateTime1, "-");
        testCaseTwo = new Assignment("Assignment 5", "CS2102", testDateTime1, "-");
        testCaseThree = new Assignment("OP1", "CS2101", testDateTime3, "15%");
        testCaseFour = new Event("midterms", "MPSH1A", testDateTime1, testDateTime2, "-");
        testCaseFive = new Event("Countdown", "TimeSquare", testDateTime3, testDateTime4, "new year new me");
        testCaseSix = new Event("mid", "MPSH1A", testDateTime1, testDateTime2, "-");
        testCaseSeven = new Assignment("Test 5", "CS2102", testDateTime1, "-");
        filledTaskList.addTask(testCaseOne);
        filledTaskList.addTask(testCaseTwo);
        filledTaskList.addTask(testCaseThree);
        filledTaskList.addTask(testCaseFour);
        filledTaskList.addTask(testCaseFive);
        filledTaskList.addTask(testCaseSix);
        filledTaskList.addTask(testCaseSeven);
    }

    private String searchSingleEvent() {
        searchString.append(Messages.SEARCH_SUCCESS_MESSAGE);
        searchString.append(System.lineSeparator());
        searchString.append("  4.[E][X] midterms (at: MPSH1A | Fri 13 Mar 2020 18:00 - 20:30)");
        searchString.append(System.lineSeparator());
        searchString.append("            notes: -");
        searchString.append(System.lineSeparator());
        return searchString.toString();
    }

    private String searchMultipleEvents() {
        searchString.append(Messages.SEARCH_SUCCESS_MESSAGE);
        searchString.append(System.lineSeparator());
        searchString.append("  4.[E][X] midterms (at: MPSH1A | Fri 13 Mar 2020 18:00 - 20:30)");
        searchString.append(System.lineSeparator());
        searchString.append("            notes: -");
        searchString.append(System.lineSeparator());
        searchString.append("  6.[E][X] mid (at: MPSH1A | Fri 13 Mar 2020 18:00 - 20:30)");
        searchString.append(System.lineSeparator());
        searchString.append("            notes: -");
        searchString.append(System.lineSeparator());
        return searchString.toString();
    }

    private String searchSingleAssignment() {
        searchString.append(Messages.SEARCH_SUCCESS_MESSAGE);
        searchString.append(System.lineSeparator());
        searchString.append("  1.[A][X] Test 3 (by: Fri 13 Mar 2020 18:00 | mod: CS2102)");
        searchString.append(System.lineSeparator());
        searchString.append("            notes: -");
        searchString.append(System.lineSeparator());
        return searchString.toString();
    }

    private String searchMultipleAssignments() {
        searchString.append(Messages.SEARCH_SUCCESS_MESSAGE);
        searchString.append(System.lineSeparator());
        searchString.append("  1.[A][X] Test 3 (by: Fri 13 Mar 2020 18:00 | mod: CS2102)");
        searchString.append(System.lineSeparator());
        searchString.append("            notes: -");
        searchString.append(System.lineSeparator());
        searchString.append("  7.[A][X] Test 5 (by: Fri 13 Mar 2020 18:00 | mod: CS2102)");
        searchString.append(System.lineSeparator());
        searchString.append("            notes: -");
        searchString.append(System.lineSeparator());
        return searchString.toString();
    }

    @Test
    public void executeMethod_emptyTaskList() {
        assertEquals(new SearchCommand("test", "all", null).execute(emptyTaskList,ui).feedbackToUser,
                Messages.EMPTY_TASKLIST_MESSAGE);
        assertEquals(new SearchCommand("test", "assignment", null).execute(emptyTaskList, ui).feedbackToUser,
                Messages.EMPTY_TASKLIST_MESSAGE);
        assertEquals(new SearchCommand("test", "event", null).execute(emptyTaskList, ui).feedbackToUser,
                Messages.EMPTY_TASKLIST_MESSAGE);
        assertEquals(new SearchCommand("test", "all",testDate1).execute(emptyTaskList,ui).feedbackToUser,
                Messages.EMPTY_TASKLIST_MESSAGE);
        assertEquals(new SearchCommand("test", "assignment",testDate1).execute(emptyTaskList, ui).feedbackToUser,
                Messages.EMPTY_TASKLIST_MESSAGE);
        assertEquals(new SearchCommand("test", "event",testDate1).execute(emptyTaskList, ui).feedbackToUser,
                Messages.EMPTY_TASKLIST_MESSAGE);

    }

    @Test
    public void executeMethod_emptyResults() {
        assertEquals(new SearchCommand("abcd","event", testDate1).execute(filledTaskList, ui).feedbackToUser,
                Messages.EMPTY_SEARCH_RESULTS_ERROR);
        assertEquals(new SearchCommand("abcd", "assignment", testDate1).execute(filledTaskList, ui).feedbackToUser,
                Messages.EMPTY_SEARCH_RESULTS_ERROR);
        assertEquals(new SearchCommand("abcd", "all", testDate1).execute(filledTaskList, ui).feedbackToUser,
                Messages.EMPTY_SEARCH_RESULTS_ERROR);
        assertEquals(new SearchCommand("abcd","event", null).execute(filledTaskList, ui).feedbackToUser,
                Messages.EMPTY_SEARCH_RESULTS_ERROR);
        assertEquals(new SearchCommand("abcd", "assignment", null).execute(filledTaskList, ui).feedbackToUser,
                Messages.EMPTY_SEARCH_RESULTS_ERROR);
        assertEquals(new SearchCommand("abcd", "all", null).execute(filledTaskList, ui).feedbackToUser,
                Messages.EMPTY_SEARCH_RESULTS_ERROR);
    }

    @Test
    public void executeMethod_searchOneEvent_success() {
        assertEquals(new SearchCommand("midterms", "event", null).execute(filledTaskList, ui).feedbackToUser,
                searchSingleEvent());
    }

    @Test
    public void executeMethod_searchdOneEvent_success() {
        assertEquals(new SearchCommand("midterms", "event", testDate1).execute(filledTaskList, ui).feedbackToUser,
                searchSingleEvent());
    }

    @Test
    public void executeMethod_searchMultipleEvents_success() {
        assertEquals(new SearchCommand("mid", "event", null).execute(filledTaskList, ui).feedbackToUser,
                searchMultipleEvents());
    }

    @Test
    public void executeMethod_searchdMultipleEvents_success() {
        assertEquals(new SearchCommand("mid", "event", testDate1).execute(filledTaskList, ui).feedbackToUser,
                searchMultipleEvents());
    }

    @Test
    public void executeMethod_searchSingleAssignment_success() {
        assertEquals(new SearchCommand("test 3", "assignment", null).execute(filledTaskList, ui).feedbackToUser,
                searchSingleAssignment());
    }

    @Test
    public void executeMethod_searchdSingleAssignment_success() {
        assertEquals(new SearchCommand("test 3", "assignment", testDate1).execute(filledTaskList, ui).feedbackToUser,
                searchSingleAssignment());
    }

    @Test
    public void executeMethod_searchMultipleAssignments() {
        assertEquals(new SearchCommand("test", "assignment",null).execute(filledTaskList,ui).feedbackToUser,
                searchMultipleAssignments());
    }

    @Test
    public void executeMethod_searchdMultipleAssignments() {
        assertEquals(new SearchCommand("test", "assignment", testDate1).execute(filledTaskList,ui).feedbackToUser,
                searchMultipleAssignments());
    }

    @Test
    public void executeMethod_searchAllSingleResult() {
        assertEquals(new SearchCommand("midterms", "all", null).execute(filledTaskList,ui).feedbackToUser,
                searchSingleEvent());
    }

    @Test
    public void executeMethod_searchdAllSingleResult() {
        assertEquals(new SearchCommand("midterms", "all", testDate1).execute(filledTaskList,ui).feedbackToUser,
                searchSingleEvent());
    }

    @Test
    public void executeMethod_searchMultipleResults() {
        assertEquals(new SearchCommand("test 3", "all", null).execute(filledTaskList, ui).feedbackToUser,
                searchSingleAssignment());
    }

    @Test
    public void executeMethod_searchdMultipleResults() {
        assertEquals(new SearchCommand("test", "all", null).execute(filledTaskList,ui).feedbackToUser,
                searchMultipleAssignments());
    }
    //@@author
}
