module.exports = function (grunt) {
	grunt.loadNpmTasks('grunt-angular-gettext');
	grunt.initConfig({
		  nggettext_extract: {
		    pot: {
		      files: {
		        'po/template.pot': ['**/*.html']
		      }
		    },
		  },
		  nggettext_compile: {
				all: {
					files: {
						'scripts/translations.js': ['po/*.po']
					}
				},
			},
		})
		
};