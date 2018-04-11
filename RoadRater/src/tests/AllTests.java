package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BinarySearchSTTest.class, DataParserTest.class, GraphTest.class, RoadTest.class, SortNameTest.class,
		SortRankTest.class })
public class AllTests {

}
