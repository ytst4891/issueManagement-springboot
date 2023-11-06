package com.example.its2.web.issue;

import com.example.its2.domain.issue.IssueEntity;
import com.example.its2.domain.issue.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    // GET /issues
    @GetMapping("/issues")
    public String showList(Model model) {
        model.addAttribute("issueList", issueService.findAll());
        return "issues/list";
    }

    // GET /issues/creationform
    @GetMapping("/issues/creationform")
    public String showCreationForm(@ModelAttribute IssueForm form) {
        return "issues/creationform";
    }

    // POST /issues/
    @PostMapping("/issues")
    public String create(@Validated IssueForm form, BindingResult bindingResult) {

        // validationエラー時に入力画面に戻す。
        if (bindingResult.hasErrors()) {
            return showCreationForm(form);
        }

        issueService.create(form.getSummary(), form.getDescription());
        return "redirect:/issues";
    }

    // GET /issues/1
    @GetMapping("/issues/{issueId}")
    public String showDetail(@PathVariable("issueId") long issueId, Model model) {
        model.addAttribute("issue", issueService.findById(issueId));
        return "issues/detail";
    }

}
