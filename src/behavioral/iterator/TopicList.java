package behavioral.iterator;

public class TopicList implements Container<String> {
    private String[] topics;
    private int position;

    public TopicList(String[] topics) {
        this.topics = topics;
        this.position = 0;
    }

    @Override
    public Iterator<String> getIterator() {
        return new TopicIterator();
    }

    private class TopicIterator implements Iterator<String> {
        private int currentPosition = 0;

        @Override
        public boolean hasNext() {
            return currentPosition < topics.length;
        }

        @Override
        public String next() {
            if (hasNext()) {
                return topics[currentPosition++];
            }
            return null;
        }

        @Override
        public void reset() {
            currentPosition = 0;
        }
    }
}
