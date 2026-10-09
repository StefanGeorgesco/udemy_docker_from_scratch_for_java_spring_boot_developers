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
    },
    {
        description: "Platform Engineer",
        company: "Netflix",
        skills: ["Linux", "Terraform", "Kubernetes", "Docker", "Go"],
        salary: 140000,
        isRemote: true
    },
    {
        description: "Full Stack Engineer",
        company: "Stripe",
        skills: ["JavaScript", "Node.js", "React", "SQL"],
        salary: 125000,
        isRemote: true
    },
    {
        description: "Security Engineer",
        company: "Cisco",
        skills: ["Python", "Cybersecurity", "AWS", "Terraform"],
        salary: 135000,
        isRemote: false
    },
    {
        description: "Cloud Solutions Architect",
        company: "Oracle",
        skills: ["AWS", "Azure", "Architecture", "Docker", "Kubernetes"],
        salary: 150000,
        isRemote: false
    },
    {
        description: "Mobile Developer",
        company: "Spotify",
        skills: ["Swift", "Kotlin", "iOS", "Android"],
        salary: 117000,
        isRemote: true
    },
    {
        description: "Backend Engineer",
        company: "Uber",
        skills: ["Java", "Spring Boot", "Redis", "Kafka"],
        salary: 128000,
        isRemote: true
    },
    {
        description: "Machine Learning Engineer",
        company: "OpenAI",
        skills: ["Python", "PyTorch", "Machine Learning", "MLOps"],
        salary: 170000,
        isRemote: true
    },
    {
        description: "QA Automation Engineer",
        company: "Salesforce",
        skills: ["Selenium", "JavaScript", "Cypress", "Testing"],
        salary: 112000,
        isRemote: false
    },
    {
        description: "Site Reliability Engineer",
        company: "GitHub",
        skills: ["Linux", "Monitoring", "Go", "Kubernetes", "CI/CD"],
        salary: 145000,
        isRemote: true
    },
    {
        description: "Data Engineer",
        company: "Snowflake",
        skills: ["SQL", "Python", "Airflow", "ETL", "AWS"],
        salary: 132000,
        isRemote: false
    },
    {
        description: "Product Engineer",
        company: "Dropbox",
        skills: ["TypeScript", "React", "Node.js", "Product Thinking"],
        salary: 121000,
        isRemote: true
    },
    {
        description: "AI Engineer",
        company: "NVIDIA",
        skills: ["Python", "C++", "Deep Learning", "CUDA"],
        salary: 175000,
        isRemote: false
    }
]);