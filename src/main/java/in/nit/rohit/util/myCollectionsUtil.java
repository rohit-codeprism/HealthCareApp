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
	}
}
