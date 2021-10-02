package in.nit.rohit.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// jdk 8(static method + default method allowed)
public interface myCollectionsUtil {

	public static Map<Long, String> convertToMap(List<Object[]> list )
	{
		Map<Long,String> map = list
				.stream()
				.collect(Collectors.toMap(
						ob->Long.valueOf(ob[0].toString()),

					    ob->ob[1].toString()));
		return map;
		
		
		/*
		 * //JDK 9
		List<Object[]> list = List.of(
				new Object[] {10,"AA"},
				new Object[] {11,"BB"},
				new Object[] {12,"CC"},
				new Object[] {13,"DD"},
				new Object[] {14,"EE"}
				);

		//Java 8 Streams
		Map<Integer,String> map =
				list
				.stream()
				.collect(Collectors.toMap(
						ob->Integer.valueOf(
								ob[0].toString()), 
						ob->ob[1].toString()));
		//for Each
		Map<Integer,String> map2 = new HashMap<>();
		for(Object[] ob:list) {
			map2.put(
					Integer.valueOf(ob[0].toString()), 
					ob[1].toString());
		 */
	}
}
