/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pratikum.demo;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Maulana Alfiansyah
 */
@RestController
public class ProductserviceController {
   private static Map<String, Product> productRepo = new HashMap<>();
   static {
 
      Product honey = new Product();
      honey.setId("1");
      honey.setName("Kacang");
      honey.setQty("1");
      honey.setPrice("Rp. 10000");
      productRepo.put(honey.getId(), honey);
      
      Product almond = new Product();
      almond.setId("2");
      almond.setName("Roti");
      almond.setQty("1");
      almond.setPrice("Rp. 20000");
      productRepo.put(almond.getId(), almond);
     
   }
   //menghapus produk
   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> delete(@PathVariable("id") String id, @RequestBody Product product) { 
      productRepo.remove(id);
      return new ResponseEntity<>("Produk Terhapus", HttpStatus.OK);
   }
   //mengupdate data
   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
       //Tampilan produk yg akan di update tidak ditemukan 
      if(!productRepo.containsKey(id)){
            return new ResponseEntity<>("Produk Tidak Ada", HttpStatus.NOT_FOUND);
        }
        else{
          //Tampilan produk apabila berhasil terupdate
            productRepo.remove(id);
            product.setId(id);
            productRepo.put(id, product);
            return new  ResponseEntity<>("Success Update Data",HttpStatus.OK);
        }

    }
   
   @RequestMapping(value = "/products", method = RequestMethod.POST)
   public ResponseEntity<Object> createProduct(@RequestBody Product product) {
     if(productRepo.containsKey(product.getId())){
         //Tampilan apabila id produk yg di tambahkan sudah ada 
            return new ResponseEntity<>("ID Produk tidak boleh sama", HttpStatus.OK);
        }
        else{
         //Tampilan berhasil menambahkan produk
            productRepo.put(product.getId(), product);
            return new ResponseEntity<>("Produk Berhasil di Tambahkan", HttpStatus.CREATED);
        }
    }
   
   //menampilan produk
   @RequestMapping(value = "/products")
   public ResponseEntity<Object> getProduct() {
      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
   }
}
    
    

