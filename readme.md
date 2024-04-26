# KURULUMLAR VE PROJE TEKNOLOJILERI

## Git Komutlari:
   
    ***** Degisiklikleri kaydetmek
    Commit kismindan commit and push yapıyoruz
    Terminal ekraninda git checkout master yazıyoruz
    Daha sonra git merge umit yaziyoruz
    git push -u origin master yaziyoruz
    (Eğer bu kısımda çakışma sorunu verirse git pull origin master komutu ile verileri çekiyoruz.)
    ve tekrar master dan umit e gecip kod yazmaya devam ediyoruz
 
    ***** Degisiklikleri kendi projene cekmek icin;
    git pull origin master

## MongoDB Entegrasyonu:

```bash
    docker run --name java13MongoDB -d -e "MONGO_INITDB_ROOT_USERNAME=admin" -e "MONGO_INITDB_ROOT_PASSWORD=root" -p 27017:27017 mongo:jammy 
```

    Yetkilendirme İşlemleri:
    1- MONGOSH'a tıklayarak açıyorsunuz.
    2- Açılan kısımda test> şeklinde bir yer göreceksiniz, öncelikle test DB'den kendi DB'nize geçmek için 
    use [DB_adı] yazıp enter'a basınız.
    Örn: 
    use CompanyDB
    switched to db CompanyDB
     CompanyDB > şeklinde bir görüntü elde edeceksiniz.
    3- Burada kullanıcı oluturmak için gerekli komutları giriyoruz.

```
     db.createUser({ user: "bilgeUser", pwd: "bilgeUser*", roles: ["readWrite","dbAdmin"]})
```


## YAPILACAKLAR

GENEL DTO'suna email ve anotasyonlar vs eklenecek (NE EKSİK OLURSA)

EG: private EKLENECEK ENTITYLERE!! 
     