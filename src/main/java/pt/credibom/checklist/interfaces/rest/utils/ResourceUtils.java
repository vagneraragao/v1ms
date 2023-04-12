package pt.credibom.checklist.interfaces.rest.utils;

import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResourceUtils {

	/**
	 * method calculates list of sort orders from an array of strings
	 * e.g. [id, asc], [id, asc][name, asc], [id][name,desc], etc.
	 * @param sort
	 * @return List<Sort.Order>
	 */
	public static List<Sort.Order> toSortOrders( String[] sort ) {
		return sort.length == 1 || ( sort.length == 2 &&
				( sort[ 1 ].toUpperCase().equals( Sort.Direction.ASC.name() ) ||
						sort[ 1 ].toUpperCase().equals( Sort.Direction.DESC.name() ) ) )
				? sort.length > 1
					? List.of( new Sort.Order( Sort.Direction.fromString( sort[ 1 ] ), sort[ 0 ] ) )
					: List.of( new Sort.Order( Sort.Direction.ASC, sort[ 0 ] ) )
				: Arrays.stream( sort )
					.map( sO -> {

						String[] sortProperties = sO.split( "," );
						return sortProperties.length > 1
								? new Sort.Order( Sort.Direction.fromString( sortProperties[ 1 ] ), sortProperties[ 0 ] )
								: new Sort.Order( Sort.Direction.ASC, sortProperties[ 0 ] );

					} )
					.collect( Collectors.toList() );
	}
}
