public abstract class ToolFactory {

    public Tools[] makeTools(String type){
        Tools[] tools = new Tools[24];
        for(int i = 0; i < 24; i++){
            tools[i] = createTools(type);
        }
        return tools;
    }
    protected abstract Tools createTools(String type);
}
