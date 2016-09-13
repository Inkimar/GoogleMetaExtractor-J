fetch-image:
	@echo "needs wget"
	cd resources && wget  https://upload.wikimedia.org/wikipedia/commons/7/7c/Nymphalis_antiopa.JPG

build-no_test:
	@echo "needs maven"
	./run-skip_tests.sh

build-with_test:
	@echo "needs maven"
	./run.sh

