		/*
		 * ArrayList<String> cases1 = new ArrayList<String>(); cases1.add("a");
		 * cases1.add("b"); cases1.add("bab"); cases1.add("abb");
		 * cases1.add("bbaba"); cases1.add("babaab"); cases1.add("bbbababab");
		 * cases1.add("bbbbb"); String[] finalStates1 = { "b", "bab" }; String[]
		 * alphabet1 = { "a", "b" }; String[][] matrix1 = { { " ", "a", "4" }, {
		 * " ", "b", "b" }, { "b", "a", "ba" }, { "b", "b", "b" }, { "ba", "a",
		 * "4" }, { "ba", "b", "bab" }, { "bab", "a", "ba" }, { "bab", "b",
		 * "bab" } }; DFSA2 FiniteStateAutomaton1 = new DFSA2(4, finalStates1,
		 * alphabet1, matrix1, cases1);
		 * System.out.println(caseTest(FiniteStateAutomaton1));
		 * 
		 * ArrayList<String> cases2 = new ArrayList<String>(); cases2.add("");
		 * cases2.add("bb"); cases2.add("aaab"); cases2.add("babbb");
		 * cases2.add("ababab"); cases2.add("bbaabba"); cases2.add("abababbba");
		 * cases2.add("bbb"); String[] finalStates2 = { " ", "b", "bb" };
		 * String[] alphabet2 = { "a", "b" }; String[][] matrix2 = { { " ", "a",
		 * " " }, { " ", "b", "b" }, { "b", "a", " " }, { "b", "b", "bb" }, {
		 * "bb", "a", " " }, { "bb", "b", "3" } }; DFSA2 FiniteStateAutomaton2 =
		 * new DFSA2(3, finalStates2, alphabet2, matrix2, cases2);
		 * System.out.println(caseTest(FiniteStateAutomaton2));
		 */