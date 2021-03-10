####  编写项目中遇到的问题

#### 1：使用了Component就无法使用@Autowire
    
    public class UserComponent {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean userCheck(String currentPassword, String password) {
        return this.bCryptPasswordEncoder.matches(currentPassword, password);
    }
}