package w3t4.Schnittstellen;

public abstract class AbstractMitarbeiter // implements IPrintable
{

	private String name;

	public AbstractMitarbeiter(String name) {
		this.name = name;

	}

	public String getName() {
		return name;
	}

	public abstract double getGehalt();

}
