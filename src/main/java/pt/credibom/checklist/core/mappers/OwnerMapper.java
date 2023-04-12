package pt.credibom.checklist.core.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pt.credibom.checklist.domain.pendingdocumentation.Owner;
import pt.credibom.checklist.interfaces.command.OwnerCommand;

@Component
@RequiredArgsConstructor
public class OwnerMapper {

    public Owner fromCommandToEntity(OwnerCommand command ) {
        return Owner.builder()
                .id(  command.getId() )
                .active( command.isActive() )
                .description( command.getDescription() )
                .build();
    }
}
