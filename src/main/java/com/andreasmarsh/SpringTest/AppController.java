package com.andreasmarsh.SpringTest;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private MovieRepository movieRepo;

    @Autowired
    private PromotionRepository promoRepo;

    @Autowired
    private UserServices service;

    @Autowired
    private MovieServices movieService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/process_register")
    public String processRegister(User user, Address address, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {

        String[] cardIDs = request.getParameterValues("cardID");
        String[] cardTypes = request.getParameterValues("cardType");
        String[] cardNumbers = request.getParameterValues("cardNumber");
        String[] cardMonths = request.getParameterValues("expMonth");
        String[] cardYears = request.getParameterValues("expYear");
        String[] cardCvvs = request.getParameterValues("cvv");

        System.out.println(cardNumbers.length + " cards");
        for (int i = 0; i < 3; i++) {
            System.out.println(cardNumbers[i]);
            if (!cardNumbers[i].equals("")) {
                user.addCreditCard(cardTypes[i], Long.parseLong(cardNumbers[i]), Long.parseLong(cardMonths[i]), Long.parseLong(cardYears[i]), Long.parseLong(cardCvvs[i]));
            } else {
                user.addCreditCard(null, null, null, null, null);
            }
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            if(repo.doesEmailExist(user.getEmail()) == BigInteger.valueOf(0)) {
                service.register(user, address, getSiteURL(request));
            } else {
                return "user_exists";
            }

            return "register_success";
        } else {
            System.out.println(user.getId());
            service.update(user, address, getSiteURL(request));
        }
        return "redirect:/";
    }

    @PostMapping("/process_reset")
    public String processReset(HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {

        String[] emails = request.getParameterValues("email");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

            if(repo.doesEmailExist(emails[0]) == BigInteger.valueOf(1)) {
                service.reset(emails[0], getSiteURL(request));

                return "reset_success";
            } else {
                return "reset_form_failure";
            }
        }

        return "redirect:/";
    }

    @PostMapping("/process_edit")
    public String processEdit(User user, Address address, HttpServletRequest request, Model model)
            throws UnsupportedEncodingException, MessagingException {

        String[] cardIDs = request.getParameterValues("cardID");
        String[] cardTypes = request.getParameterValues("cardType");
        String[] cardNumbers = request.getParameterValues("cardNumber");
        String[] cardMonths = request.getParameterValues("expMonth");
        String[] cardYears = request.getParameterValues("expYear");
        String[] cardCvvs = request.getParameterValues("cvv");

        String[] currentPasswords = request.getParameterValues("currentPwd");
        String[] newPasswords = request.getParameterValues("newPwd");


        //System.out.println(cardNumbers.length + " cards");
        for (int i = 0; i < 3; i++) {
            System.out.println(cardNumbers[i]);
            if (!cardNumbers[i].equals("")) {
                user.setCreditCard(Long.parseLong(cardIDs[i]), cardTypes[i], Long.parseLong(cardNumbers[i]), Long.parseLong(cardMonths[i]), Long.parseLong(cardYears[i]), Long.parseLong(cardCvvs[i]));
            } else {
                user.setCreditCard(Long.parseLong(cardIDs[i]), null, null, null, null, null);
            }
        }

        System.out.println(currentPasswords[0]);
        System.out.println(newPasswords[0]);
        System.out.println(passwordEncoder.encode(currentPasswords[0]));
        System.out.println(user.getPassword());

        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        if(b.matches(currentPasswords[0], user.getPassword()) && !(newPasswords[0].equals(""))) {
            String encodedPassword = passwordEncoder.encode(newPasswords[0]);
            user.setPassword(encodedPassword);
            service.update(user, address, getSiteURL(request));
            return "edit_success";
        } else if(currentPasswords[0].equals("") && newPasswords[0].equals("")){
            System.out.println(user.getId());
            service.update(user, address, getSiteURL(request));
            return "edit_success";
        } else {
            user.setAddress(address);
            return showEditProfileFormFailure(user, model);
        }
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @Autowired
    private UserRepository userRepo;

    @GetMapping("")
    public String viewHomePage(Model model) {
        List<Movie> listMoviesNowShowing = movieRepo.findByNowShowing(true);
        List<Movie> listMoviesComingSoon = movieRepo.findByNowShowing(false);

        model.addAttribute("listMoviesNowShowing", listMoviesNowShowing);
        model.addAttribute("listMoviesComingSoon", listMoviesComingSoon);
        return "homepage";
    }

    @GetMapping("/error")
    public String showErrorPage() {
        return "error";
    }

    @GetMapping("/profile")
    public String showProfilePage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = (User) repo.findByEmail(currentUser.getUsername());
        model.addAttribute("currentUser", user);

        return "profile";
    }

    @GetMapping("/admin-portal")
    public String showAdminPortal(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = (User) repo.findByEmail(currentUser.getUsername());
        model.addAttribute("currentUser", user);

        return "admin-portal";
    }

    @GetMapping("/manage-movies")
    public String listMovies(Model model) {
        List<Movie> listMovies = movieRepo.findAll();
        model.addAttribute("listMovies", listMovies);

        return "manage-movies";
    }

    @GetMapping("/manage-promotions")
    public String listPromotions(Model model) {
        List<Promotion> listPromotions = promoRepo.findAll();
        model.addAttribute("listPromotions", listPromotions);

        return "promo-sent";
    }

    @GetMapping("/promo-sent")
    public String listPromotions2(Model model) {
        List<Promotion> listPromotions = promoRepo.findAll();
        model.addAttribute("listPromotions", listPromotions);

        return "promo-sent";
    }

    @GetMapping("/promotion-form")
    public String promotionForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
        model.addAttribute("promotion", new Promotion());

        return "promotion-form";
        //}
    }

    @PostMapping("/process_addPromotion")
    public String processAddPromotion(Promotion promo, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException, ParseException {


        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(promo.getStartStringDate());
        Date date2=new SimpleDateFormat("HH:mm").parse(promo.getStartStringTime());
        //promo.setStartTime(date);
        promo.setStartDate(date);
        promo.setStartTime(date2);

        Date date3=new SimpleDateFormat("yyyy-MM-dd").parse(promo.getEndStringDate());
        Date date4=new SimpleDateFormat("HH:mm").parse(promo.getEndStringTime());

        promo.setEndDate(date3);
        promo.setEndTime(date4);

        Promotion savedPromo = promoRepo.save(promo);

        return "redirect:/manage-promotions";
    }

    @GetMapping("/edit-promotion/{id}")
    public String showEditPromotionForm(@PathVariable("id") Integer id, Model model) {
        System.out.println(id);
        Promotion promo = promoRepo.findById(Long.valueOf(id)).get();

        model.addAttribute("promotion", promo);

        System.out.println("here");

        return "edit-promotion-page";
    }

    @PostMapping("/edit-promotion-process")
    public String processEditPromotion(Promotion promo, HttpServletRequest request, Model model)
            throws UnsupportedEncodingException, MessagingException, ParseException {

        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(promo.getStartStringDate());
        Date date2=new SimpleDateFormat("HH:mm").parse(promo.getStartStringTime());
        //promo.setStartTime(date);
        promo.setStartDate(date);
        promo.setStartTime(date2);

        Date date3=new SimpleDateFormat("yyyy-MM-dd").parse(promo.getEndStringDate());
        Date date4=new SimpleDateFormat("HH:mm").parse(promo.getEndStringTime());

        promo.setEndDate(date3);
        promo.setEndTime(date4);

        promoRepo.save(promo);

        return "redirect:/manage-promotions";
    }

    @GetMapping("/movie-form")
    public String movieForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
        model.addAttribute("user", new User());
        model.addAttribute("address", new Address());
        model.addAttribute("creditcard", new CreditCard());
        model.addAttribute("movie", new Movie());
        model.addAttribute("movieshowing", new MovieShowing());

        return "movie-form";
        //}
    }


    @PostMapping("/process_addMovie")
    public String processRegisterMovie(Movie movie, MovieShowing showing, Review review, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException, ParseException {

/** this will be for review
        //String[] reviewIDs = request.getParameterValues("cardID");
        String[] ratings = request.getParameterValues("rating"); //rating - critic's score
        String[] reviews = request.getParameterValues("review"); //review - written review
 */

        /**
        System.out.println(cardNumbers.length + " cards");
        for (int i = 0; i < 3; i++) {
            System.out.println(cardNumbers[i]);
            if (!cardNumbers[i].equals("")) {
                user.addCreditCard(cardTypes[i], Long.parseLong(cardNumbers[i]), Long.parseLong(cardMonths[i]), Long.parseLong(cardYears[i]), Long.parseLong(cardCvvs[i]));
            } else {
                user.addCreditCard(null, null, null, null, null);
            }
        }
        */

        //Date date1=new SimpleDateFormat("yyyy/MM/dd").parse(showing.getStringDate());
        //showing.setDate(date1);
        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(showing.getStringDate());
        Date date2=new SimpleDateFormat("HH:mm").parse(showing.getStringTime());
        //promo.setStartTime(date);
        showing.setDate(date);
        showing.setTime(date2);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {

        }
        movieService.addMovie(movie, showing, review, getSiteURL(request));
        return "redirect:/manage-movies";
    }

    @GetMapping("/update-info")
    public String showEditForm() {
        return "update-info";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("user", new User());
            model.addAttribute("address", new Address());
            model.addAttribute("creditcard", new CreditCard());
            return "signup_form";
        }

        return "redirect:/";
    }

    @GetMapping("/reset")
    public String showResetForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "reset_form";
        }

        return "redirect:/";
    }

    @GetMapping("/view-movie/{id}")
    public String showMovieDetails(@PathVariable("id") Integer id, Model model) {
        Movie movie = movieRepo.findById(Long.valueOf(id)).get();
        model.addAttribute("movie", movie);

        return "view-movie";
    }

    @GetMapping("/edit-profile/{id}")
    public String showEditProfileForm(@PathVariable("id") Integer id, Model model) {
        System.out.println(id);
        User user = repo.findById(Long.valueOf(id)).get();
        model.addAttribute("user", user);

        model.addAttribute("address", user.getAddress());

        return "edit_form";
    }

    @GetMapping("/delete-promotion/{id}")
    public String showDeletePromotionForm(@PathVariable("id") Integer id, Model model) {
        //System.out.println(id);
        Promotion promo = promoRepo.findById(Long.valueOf(id)).get();
        model.addAttribute("promo", promo);

        return "delete-promotion";
    }

    @PostMapping("/process_delete_promotion")
    public String processDeletePromotion(Promotion promotion) {
        //System.out.println(promotion.getPromotionID());
        service.deletePromotion(promotion);
        return "redirect:/manage-promotions";
    }

    @GetMapping("/send-promotion/{id}")
    public String showSendPromotionForm(@PathVariable("id") Integer id, Model model) {
        //System.out.println(id);
        Promotion promo = promoRepo.findById(Long.valueOf(id)).get();
        model.addAttribute("promo", promo);

        return "send-promo";
    }

    @PostMapping("/process_send_promotion")
    public String processSendPromotion(Promotion promotion,  HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        //System.out.println(promotion.getPromotionID());
        promotion = promoRepo.getById(promotion.getPromotionID());
        service.sendPromotion(promotion, getSiteURL(request));
        return "redirect:/promo-sent";
    }

    @GetMapping("/delete-movie/{id}")
    public String showDeleteMovieForm(@PathVariable("id") Integer id, Model model) {
        //System.out.println(id);
        Movie movie = movieRepo.findById(Long.valueOf(id)).get();
        model.addAttribute("movie", movie);

        return "delete-movie";
    }

    @PostMapping("/process_delete_movie")
    public String processDeleteMovie(Movie movie) {
        //System.out.println(promotion.getPromotionID());
        service.deleteMovie(movie);
        return "redirect:/manage-movies";
    }

    @GetMapping("/edit-profile-failure")
    public String showEditProfileFormFailure(User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("address", user.getAddress());

        return "edit_form_failure";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (service.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }

        return "redirect:/";
    }

    @GetMapping("/login_error")
    public String loginFailurePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login_error";
        }

        return "redirect:/";
    }
}
