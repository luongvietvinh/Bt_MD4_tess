package vinhsama.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import vinhsama.model.Categories;
import vinhsama.model.Product;
import vinhsama.service.ICategories;
import vinhsama.service.IProductService;
import vinhsama.service.ProductService;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    IProductService productService;
    @Autowired
    ICategories categoriesService;

    @GetMapping ("/product")
    public ModelAndView showAll(){
        ModelAndView modelAndView = new ModelAndView("ShowProduct");
        modelAndView.addObject("products" , productService.findAll() );
        return modelAndView;
    }
    @GetMapping ("/create")
    public ModelAndView add(){
    ModelAndView modelAndView = new ModelAndView("CreateProduct");
    modelAndView.addObject("products" , new Product());
    modelAndView.addObject("categories" , categoriesService.findAll());
    return modelAndView;
    }
    @PostMapping("/create")
    public String createProduct(@ModelAttribute  Product product, @RequestParam MultipartFile uppImg, @RequestParam int idcategori) {
        Categories categories = new Categories();
        categories.setId(idcategori);
        product.setCategories(categories);
        String filename = uppImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(uppImg.getBytes(),new File("C:\\Users\\Admind\\Desktop\\bt_MD4_Tuan2\\src\\main\\webapp\\WEB-INF\\view\\img/" + filename));
            product.setImg("/i/view/img/" +filename);
            productService.save(product);
        } catch (IOException e) {
            product.setImg("");
            productService.save(product);
            e.printStackTrace();
        }
        return "redirect:/product";
    }

    @GetMapping ("/edit")
    public ModelAndView edit(@RequestParam long id){
        ModelAndView modelAndView = new ModelAndView("EditProduct");
        modelAndView.addObject("products" , productService.findById(id));
        modelAndView.addObject("categories" , categoriesService.findAll());
        return modelAndView;
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute  Product product,@RequestParam long id, @RequestParam MultipartFile uppImg, @RequestParam long idcategori) {
        Categories categories = new Categories();
        categories.setId(idcategori);
        product.setCategories(categories);
        String filename = uppImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(uppImg.getBytes(),new File("C:\\Users\\Admind\\Desktop\\bt_MD4_Tuan2\\src\\main\\webapp\\WEB-INF\\view\\img/" + filename));
            product.setImg("/i/view/img/" +filename);
            productService.save(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imgOld =productService.findById(id).getImg();
        if(imgOld.equals(product.getImg()) && product.getImg().isEmpty()){
            String filedelete = imgOld.replaceAll("/i/img/","");
            String file1 = "C:\\Users\\Admind\\Desktop\\bt_MD4_Tuan2\\src\\main\\webapp\\WEB-INF\\view\\img/" +filedelete;
            File file = new File(file1);
            if(file.exists()){
                file.delete();
            }
        }
        return "redirect:/product";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam long id){
        Product product = productService.findById(id);
        if(product.getImg().isEmpty()){
            productService.delete(id);
            return "redirect:/product";
        }
        String filedelete = product.getImg().replaceAll("/i/view/img/","");
        String file1 = "C:\\Users\\Admind\\Desktop\\bt_MD4_Tuan2\\src\\main\\webapp\\WEB-INF\\view\\img/" +filedelete;
        File file = new File(file1);
        if(file.exists()){
            file.delete();
        }
        productService.delete(id);
        return "redirect:/product";
    }


}
