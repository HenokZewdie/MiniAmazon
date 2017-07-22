package AmazonPackage;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OrderRepostitory orderRepostitory;

    @Autowired
    private CustomerRepository customerRepo;

    @RequestMapping(value = "/")
    public String homepage(){
        return "home";
    }
    @RequestMapping("/login")
    public String login(){return "login";}
    @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
    public String loginSuccess(Principal principal, User user){

       System.out.println("THE USER ISSSSS  " );
        return "display";
    }

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public String Display(Model model, Product product, Orders orders){
        model.addAttribute("orders", new Orders());
        Iterable<Product> proList = productRepository.findAll();
        model.addAttribute("EachList", proList);
        return "display";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searching(Model model){
        model.addAttribute("custom", new Customer());
        return "search";
    }
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchPost(@ModelAttribute Customer customer, Model model){
        long min = customer.getZipCode() - 50;
        long max = customer.getZipCode() + 50;
        List<Customer> zipList = new ArrayList<>();

        for(long i=min;i<=max;i++){
            List<Customer> custList = customerRepo.findByZipCode(i);
            zipList.addAll(custList);
        }
        model.addAttribute("ZipList",zipList);
        return "searchdisplay";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String resiterGet(Model model){
        model.addAttribute("register", new User());
        return "register";
    }
    @RequestMapping (value = "/register", method = RequestMethod.POST)
    public String registerPost(@ModelAttribute User user, Model model){
        user.setDate(new Date());
        userRepository.save(user);
        return "display";
    }
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String productGet(Model model){
        model.addAttribute("product", new Product());
        return "product";
    }
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String productGettest(Model model){
        model.addAttribute("product", new Product());
        return "product";
    }
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String productPOST(@ModelAttribute Product product, Model model){
        productRepository.save(product);
        model.addAttribute(new Product());
        return "product";
    }

    @RequestMapping(value = "/ordering/{id}", method = RequestMethod.GET)
    public String POSTorders(@PathVariable("id") long id, Model model, Principal principal, User user){
        model.addAttribute("orders",new Orders());
        user = userRepository.findByUsername(principal.getName());
        Orders orders = new Orders();
        orders.setCustomerID(user.getId());
        orders.setOrderDate(new Date());
        orders.setProductID(id);
        orderRepostitory.save(orders);
        return "redirect:/display";
    }

    @RequestMapping(value = "/bootcamp/{id}", method = RequestMethod.GET)
    public String bootcamps(@PathVariable("id") long id, Model model, Customer customer){
        model.addAttribute("customer", new Customer());
        Iterable<Customer> custList = customerRepo.findById(customer.getId());
        model.addAttribute("bootlist",custList);
        return "displaybootcamp";
    }

}
