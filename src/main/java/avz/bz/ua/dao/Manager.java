package avz.bz.ua.dao;

public interface Manager {
	public String checkContent(String directOfTrans, String value);
		
	public void putValueInDict(String directOfTrans, String world, String value);

    public void saveAll();

}
