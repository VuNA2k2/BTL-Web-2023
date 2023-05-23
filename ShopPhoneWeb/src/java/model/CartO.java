/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;


public class CartO {
    private List<ItemO> items;

    public CartO() {
        items=new ArrayList<>();
    }

    public List<ItemO> getItems() {
        return items;
    }
    public int getQuantityById(int id){
        return getItemById(id).getQuantity();
    }
    private ItemO getItemById(int id){
        for(ItemO i:items){
            if(i.getProduct().getId()==id){
                return i;
            }
        }
        return null;
    }
    public void addItem(ItemO t){
        if(getItemById(t.getProduct().getId())!=null){
            ItemO m=getItemById(t.getProduct().getId());
            m.setQuantity(m.getQuantity()+t.getQuantity());
        }else
            items.add(t);
    }
    public void removeItem(int id){
        if(getItemById(id)!=null){
            items.remove(getItemById(id));
        }
    }
    public double getTotalMoney(){
        double t=0;
        for(ItemO i:items)
            t+=(i.getQuantity()*i.getPrice());
        return t;
    }
    private Product getProductById(int id,List<Product> list){
        for(Product i:list){
            if(i.getId()==id)
                return i;
        }
        return null;
    }
    public CartO(String txt,List<Product> list){
        items=new ArrayList<>();
        try{
        if(txt!=null && txt.length()!=0){
            String[] s=txt.split("/");//thay / cho dau ,
            for(String i:s){
                String[] n=i.split(":");
                int id=Integer.parseInt(n[0]);
                int quantity=Integer.parseInt(n[1]);
                Product p=getProductById(id, list);
                ItemO t=new ItemO(p, quantity, p.getPrice());
                addItem(t);
            }
        }
        }catch(NumberFormatException e){
            
        }
    }
    
}
