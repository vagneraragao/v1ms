package pt.credibom.checklist.core.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pt.credibom.checklist.domain.pendingdocumentation.Category;
import pt.credibom.checklist.interfaces.command.CategoryCommand;

@Component
@RequiredArgsConstructor
public class CategoryMapper {
	public Category fromCommandToEntity( CategoryCommand command ) {
		return Category.builder()
				.id( command.getId() )
				.active( command.isActive() )
				.description( command.getDescription() )
				.build();
	}
}
