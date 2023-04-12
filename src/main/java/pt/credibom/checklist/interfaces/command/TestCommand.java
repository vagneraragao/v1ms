package pt.credibom.checklist.interfaces.command;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestCommand implements Serializable {

	private static final long serialVersionUID = -563759386824023068L;
	
	private String name;
	
}
