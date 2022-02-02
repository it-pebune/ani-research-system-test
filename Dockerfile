FROM docker.io/markhobson/maven-chrome:latest
RUN mkdir /tmp/test_results
RUN cd /home && \
mkdir runTestsLocation && \
cd runTestsLocation && \
git clone https://github.com/it-pebune/ani-research-system-test.git && \
cd ani-research-system-test/ && \
ls -alh && \
git pull origin POC_test && \
mvn surefire-report:report
CMD cd /home/runTestsLocation/ani-research-system-test && mvn surefire-report:report && cp -r local_test_report/* /tmp/test_results && cp target/site/* /tmp/test_results