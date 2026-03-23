document.addEventListener('DOMContentLoaded', function () {

	var root = document.documentElement;
	 var storageKey = 'student-course-theme';

	 function applyTheme(theme) {
	   root.setAttribute('data-theme', theme);
	   if (toggleButton) {
	     toggleButton.innerHTML = theme === 'light' ? '🌙 Dark mode' : '☀️ Light mode';
	   }
	 }

	 var savedTheme = localStorage.getItem(storageKey) || 'dark';
	 var toggleButton = document.createElement('button');
	 toggleButton.type = 'button';
	 toggleButton.className = 'btn btn-secondary theme-toggle';
	 toggleButton.addEventListener('click', function () {
	   var nextTheme = root.getAttribute('data-theme') === 'light' ? 'dark' : 'light';
	   localStorage.setItem(storageKey, nextTheme);
	   applyTheme(nextTheme);
	 });
	 document.body.appendChild(toggleButton);
	 applyTheme(savedTheme);
	

  document.querySelectorAll('[data-confirm]').forEach(function (el) {
    el.addEventListener('submit', function (event) {
      if (!window.confirm(el.getAttribute('data-confirm'))) {
        event.preventDefault();
      }
    });
  });

  document.querySelectorAll('[data-filter-target]').forEach(function (input) {
    input.addEventListener('input', function () {
      var target = document.querySelector(input.getAttribute('data-filter-target'));
      if (!target) return;
      var term = input.value.trim().toLowerCase();
      target.querySelectorAll('[data-search-text]').forEach(function (row) {
        var haystack = row.getAttribute('data-search-text').toLowerCase();
        row.classList.toggle('hidden-row', term.length > 0 && haystack.indexOf(term) === -1);
      });
    });
  });

  document.querySelectorAll('[data-copy-text]').forEach(function (button) {
    button.addEventListener('click', function () {
      var text = button.getAttribute('data-copy-text');
      if (!navigator.clipboard) {
        return;
      }
      navigator.clipboard.writeText(text).then(function () {
        var original = button.innerHTML;
        button.innerHTML = 'Copied ✓';
        setTimeout(function () { button.innerHTML = original; }, 1200);
      });
    });
  });
});
