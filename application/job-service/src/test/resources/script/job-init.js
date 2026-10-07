// create db
db = db.getSiblingDB("job");

// create user
db.createUser({
    user: "job_user",
    pwd: "job_password",
    roles: [
        {
            role: "readWrite",
            db: "job"
        }
    ]
})

// create collection
db.createCollection("job");

// create documents
db.job.insertMany([
    {
        description: "Senior Java Developer",
        company: "Amazon",
        skills: ["Java", "Spring", "Docker"],
        salary: 120000,
        isRemote: false
    },
    {
        description: "Frontend Developer",
        company: "Google",
        skills: ["JavaScript", "React", "CSS"],
        salary: 110000,
        isRemote: true
    },
    {
        description: "Data Scientist",
        company: "Facebook",
        skills: ["Python", "Machine Learning", "Statistics"],
        salary: 130000,
        isRemote: false
    },
    {
        description: "DevOps Engineer",
        company: "Microsoft",
        skills: ["AWS", "Kubernetes", "CI/CD"],
        salary: 115000,
        isRemote: true
    }
]);