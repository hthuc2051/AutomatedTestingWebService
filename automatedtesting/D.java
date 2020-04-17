class Main{
    public static void main(String args[]){
        String name = "Saito";
        String password = "a1c2bf1890eb";
        String url = "http://localhost:9292/Saito/simba.git";

        // credentials
        CredentialsProvider cp = new UsernamePasswordCredentialsProvider(name, password);
        // clone
        File dir = new File("/tmp/abc");
        CloneCommand cc = new CloneCommand()
                .setCredentialsProvider(cp)
                .setDirectory(dir)
                .setURI(url);
        Git git = cc.call();
        // add
        AddCommand ac = git.add();
        ac.addFilepattern(".");
        try {
            ac.call();
        } catch (NoFilepatternException e) {
            e.printStackTrace();
        }

        // commit
        CommitCommand commit = git.commit();
        commit.setCommitter("TMall", "open@tmall.com")
                .setMessage("push war");
        try {
            commit.call();
        } catch (NoHeadException e) {
            e.printStackTrace();
        } catch (NoMessageException e) {
            e.printStackTrace();
        } catch (UnmergedPathException e) {
            e.printStackTrace();
        } catch (ConcurrentRefUpdateException e) {
            e.printStackTrace();
        } catch (WrongRepositoryStateException e) {
            e.printStackTrace();
        }
        // push
        PushCommand pc = git.push();
        pc.setCredentialsProvider(cp)
                .setForce(true)
                .setPushAll();
        try {
            Iterator<PushResult> it = pc.call().iterator();
            if(it.hasNext()){
                System.out.println(it.next().toString());
            }
        } catch (InvalidRemoteException e) {
            e.printStackTrace();
        }

        // cleanup
        dir.deleteOnExit();
    }
}