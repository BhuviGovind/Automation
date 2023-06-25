package etrain.info.seleniumdesign;

public interface Browser {

	public void invokeApp(String browser, String url);

	public void closeApp();

}
