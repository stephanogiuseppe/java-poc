package com.stephano.javapoc;

import com.stephano.javapoc.person.Person;
import com.stephano.javapoc.person.PersonAgeComparator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.w3c.dom.ls.LSOutput;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class JavaPocApplication {

	static ArrayList<String> anot;

	public static void main(String[] args) {
		double time1 = System.currentTimeMillis();

		// setMethods();
		// arrayListMethods();
		// queueMethods();
		// treeSetMethod();
		// hashMapMethod();
		// optionalMethod();
		// streamMethod();
		// dateMethod();
		exceptionMethod();

		double time2 = System.currentTimeMillis();
		System.out.println((time2 - time1) / 1000);

		SpringApplication.run(JavaPocApplication.class, args);
	}

	protected static void setMethods() {
		Set<String> lis = new TreeSet<>();
		lis.add("Abc");
		lis.add("Abcd");

		Set<String> li = new HashSet<>();
		li.add("Abc");

		lis.removeAll(li);

		System.out.println(lis);

		anot = new ArrayList<>();
		anot.add("Out");
		System.out.println(anot);

		// HashSet -> busca de dados no banco
		// LinkedHashSet -> mantém a ordem dos elementos
		// TreeSet -> árvore
	}

	protected static void arrayListMethods() {
		List<String> names = new ArrayList<>();

		names.add("Stephano");
		names.add("Raiani");
		names.add("Maria");
		names.set(0, "Stéphano Giuseppe");
		names.remove(1);
		System.out.println(names.contains("Maria"));

		if (names.indexOf("Stéphano Giuseppe") > -1) {
			System.out.println("Tem Stéphano");
		}

		for (String name: names) {
			System.out.println("=> " + name);
		}

		Iterator<String> namesIterator = names.iterator();
		while (namesIterator.hasNext()) {
			System.out.println("==>" + namesIterator.next());
		}

		names.clear();
		System.out.println(names.isEmpty());
	}

	protected static void queueMethods() {
		Queue<String> bankQueue = new LinkedList<>(Arrays.asList("Ste", "Rai", "Fa"));
		String name = bankQueue.poll();
		String name2 = bankQueue.peek(); // return null
		String name3 = bankQueue.element(); // return error
		System.out.println(name + " " + name2 + " " + name3);
		System.out.println(bankQueue);
	}

	protected static void treeSetMethod() {
		Set<Person> people = new HashSet<>(Arrays.asList(
				new Person("Stéphano Paiva", 25),
				new Person("Giuseppe Rajão", 25),
				new Person("Raiani Arantes", 23),
				new Person("Cassinha Chatona", 18),
				new Person("Roosy Velho", 68),
				new Person("Stéphano Paiva", 28)
		));

		Set<Person> uniquePeopleOrderedByAge = new TreeSet<>(new Person());

		uniquePeopleOrderedByAge.addAll(people);

		System.out.println(uniquePeopleOrderedByAge);
	}

	protected static void hashMapMethod() {
		 Map<Integer, Person> fifaWorldChampions = new HashMap<>();

		 ArrayList<Person> people = new ArrayList<>(Arrays.asList(
				 new Person("Stéphano", 25),
		 		new Person("Raiani", 23)
		 ));

		 people.forEach(person ->
				 fifaWorldChampions.put(fifaWorldChampions.size() + 1, people.get(fifaWorldChampions.size()))
		 );

		System.out.println(fifaWorldChampions);

		people.sort(Comparator.comparingInt(Person::getAge));

		System.out.println(people);
	}

	protected static void optionalMethod() {
		Optional<String> optionalString = Optional.ofNullable(null);
		optionalString.ifPresentOrElse(System.out::println, () -> System.out.println("String is null"));

		 // optionalString = Optional.empty();
		 // System.out.println(optionalString.orElseThrow(IllegalStateException::new));

		optionalString = Optional.of("Not null");
		optionalString.ifPresent(System.out::println);
	}

	protected static void streamMethod() {
		List<String> peopleStr = new ArrayList<>(Arrays.asList("Stéphano", "Giuseppe", "Rajão", "de", "Paiva"));
		List<Person> peopleObj = new ArrayList<>(Arrays.asList(
				new Person("Fabíola", 49),
				new Person("José", 50)
		));

		List<String> peopleFiltered = peopleStr
				.stream()
				.filter(p -> p.toLowerCase().contains("a"))
				.collect(Collectors.toList());
		System.out.println(peopleFiltered);

		String peopleS = peopleStr
				.stream()
				.limit(2)
				.map(p -> p.toLowerCase().concat(": ").concat(String.valueOf(p.length())))
				.collect(Collectors.joining(","));
		System.out.println(peopleS);

		List<String> peopleName = peopleObj
				.stream()
				.map(Person::getName)
				.collect(Collectors.toList());
		System.out.println(peopleName);
	}

	protected static void dateMethod() {
		Date dateNow = new Date();
		String dateStrInstanceNow = DateFormat.getDateInstance().format(dateNow);
		System.out.println(dateStrInstanceNow);

		String dateStrNow = DateFormat.getInstance().format(dateNow);
		System.out.println(dateStrNow);

		dateStrNow = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT).format(dateNow);
		System.out.println(dateStrNow);

		dateStrNow = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(dateNow);
		System.out.println(dateStrNow);

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:mmm");
		String formatedDate = formatter.format(dateNow);
		System.out.println(formatedDate);

		// Java 8
		LocalDate firstDay = LocalDate.EPOCH;
		LocalDate today = LocalDate.now();
		System.out.println(firstDay + " - " + today);

		System.out.println("Yesterday: " + today.minusDays(1));

		LocalTime nowTime = LocalTime.now();
		System.out.println(nowTime);

		System.out.println("1 more hour: " + nowTime.plusHours(1));

		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime.plusHours(2).plusMinutes(15));
	}

	protected static void exceptionMethod() {
		try {
			new FileInputStream("file.txt");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
	}
}
