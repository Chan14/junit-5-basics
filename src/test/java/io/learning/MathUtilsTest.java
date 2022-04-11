package io.learning;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.fail;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@EnabledOnJre(JRE.JAVA_17)
//@EnabledOnOs(OS.MAC)
class MathUtilsTest {
    MathUtils mathUtils;
    TestInfo testInfo;
    TestReporter testReporter;

    @BeforeAll
    static void start() {
        System.out.println("starting up");
    }

    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter) {
        mathUtils = new MathUtils();
        this.testInfo = testInfo;
        this.testReporter = testReporter;
        testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
    }

    @AfterEach
    void cleanup() {
        System.out.println("cleaning up");
    }

    @AfterAll
    static void end() {
        System.out.println("ending");
    }

    @Test
    @Tag("Math")
    void add() {
        int expected = 2;
        int actual = mathUtils.add(1, 1);
        Assertions.assertEquals(expected, actual, "The add method should add 1 and 1");
    }

    @Nested
    @Tag("Math")
    class SubtractTest {
        @Test
        @DisplayName("subtract same sign")
        void subtractSameSign() {
            int expected = 0;
            int actual = mathUtils.subtract(1, 1);
            Assertions.assertEquals(expected, actual, "some msg");
        }

        @Test
        @DisplayName("subtract different sign")
        void subtractDifferentSign() {
            int expected = 2;
            int actual = mathUtils.subtract(1, -1);
            Assertions.assertEquals(expected, actual, "some msg");
        }


    }

    @Test
    //@EnabledOnJre(JRE.JAVA_17)
    //@EnabledOnOs(OS.MAC)
    @Tag("Math")
    void divide() {
        boolean serverUp = false;
        Assumptions.assumeTrue(serverUp);
        Assertions.assertThrows(ArithmeticException.class, () -> mathUtils.divide(1,0), "division by zero");
    }

    @RepeatedTest(5)
    @Tag("Geometry")
    void computeCircleArea(RepetitionInfo t) {
        if (t.getCurrentRepetition() == 2)
            System.out.println("hello");

        Assertions.assertEquals(Math.PI * 10 * 10, mathUtils.computeCircleArea(10),
                "should return the area of circle of radius 10");

    }

    @Test
    @Disabled
    @DisplayName("TDD Method. should not run.")
    void tddMethod() {
        fail("failing");
    }

    @Test
    @DisplayName("multiply")
    @Tag("Math")
    void multiply() {
        /*Assertions.assertEquals(20, mathUtils.multiply(2, 10),
                "should multiply 2 and 10 and produce 20");*/
        //System.out.println("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
        //testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
        Assertions.assertAll(
                () -> Assertions.assertEquals(20, mathUtils.multiply(2, 10), "should create the right product"),
                () -> Assertions.assertEquals(20, mathUtils.multiply(-2, -10), "should create the right product"),
                () -> Assertions.assertEquals(-20, mathUtils.multiply(-2, 10), "should create the right product"),
                () -> Assertions.assertEquals(-20, mathUtils.multiply(2, -10), "should create the right product")
        );
    }
}
